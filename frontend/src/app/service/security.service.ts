import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable, OperatorFunction, throwError} from 'rxjs';
import {UserToAuth} from '../model/user-to-auth';
import {catchError} from 'rxjs/operators';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  constructor(private httpClient: HttpClient, private router: Router) {
    this.http = httpClient;
    SecurityService.INSTANCE = this;
  }

  public static base = 'http://192.168.99.100:8080/api';

  private static INSTANCE: SecurityService;

  private url = `${SecurityService.base}/security`;
  private timer: any = null;
  private http: HttpClient;

  handleError(): OperatorFunction<any, any> {
    return catchError(err => {
      const response = new HttpErrorResponse(err);
      if (response.status === 401) {
        this.logout();
        this.router.navigate(['/sign-in']);
      }
      return throwError(err);
    });
  }

  auth(user: UserToAuth): Observable<HttpResponse<any>> {
    return this.http.post(`${this.url}/auth`, user, {observe: 'response'}).pipe(this.handleError());
  }

  refresh(): void {
    const instance = SecurityService.INSTANCE;
    instance.http.post(`${instance.url}/refresh`, null, {observe: 'response'}).pipe(instance.handleError())
      .subscribe(value => instance.login(value), error => instance.logout());
  }

  login(resp: HttpResponse<any>): void {
    const token = resp.headers.get('authorization')?.split(' ')[1];
    localStorage.setItem('token', token != null ? token : '');
    this.timer = setTimeout(this.refresh, 3 * 60 * 1000);
  }

  logout(): void {
    localStorage.removeItem('token');
    this.timer = null;
  }

  getAuth(): string | null {
    return localStorage.getItem('token');
  }

  isAuthorized(): boolean {
    return this.getAuth() != null;
  }
}
