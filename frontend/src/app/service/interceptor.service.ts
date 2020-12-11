import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SecurityService} from './security.service';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor(private security: SecurityService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.security.isAuthorized()) {
      req = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' + this.security.getAuth()
        }
      });
    }
    return next.handle(req);
  }
}
