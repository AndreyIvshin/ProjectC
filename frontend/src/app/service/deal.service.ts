import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SecurityService} from './security.service';
import {Observable} from 'rxjs';
import {DealLite} from '../model/deal/deal-lite';
import {DealFull} from '../model/deal/deal-full';
import {DealToSave} from '../model/deal/deal-to-save';


@Injectable({
  providedIn: 'root'
})
export class DealService {

  constructor(private http: HttpClient, private security: SecurityService) { }

  private url = `${SecurityService.base}/deals`;

  getDealList(): Observable<DealLite[]> {
    return this.http.get(`${this.url}`).pipe(this.security.handleError());
  }

  getDeal(id: number): Observable<DealFull> {
    return this.http.get(`${this.url}/${id}`).pipe(this.security.handleError());
  }

  postDeal(unit: DealToSave): Observable<any> {
    return this.http.post(`${this.url}`, unit).pipe(this.security.handleError());
  }

  putDeal(id: number, unit: DealToSave): Observable<any> {
    return this.http.put(`${this.url}/${id}`, unit).pipe(this.security.handleError());
  }

  deleteDeal(id: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`).pipe(this.security.handleError());
  }
}
