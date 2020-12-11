import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SecurityService} from './security.service';
import {Observable} from 'rxjs';
import {UnitToSave} from '../model/unit/unit-to-save';
import {UnitLite} from '../model/unit/unit-lite';
import {UnitFull} from '../model/unit/unit-full';

@Injectable({
  providedIn: 'root'
})
export class UnitService {

  constructor(private http: HttpClient, private security: SecurityService) { }

  private url = `${SecurityService.base}/units`;

  getUnitList(): Observable<UnitLite[]> {
    return this.http.get(`${this.url}`).pipe(this.security.handleError());
  }

  getUnit(id: number): Observable<UnitFull> {
    return this.http.get(`${this.url}/${id}`).pipe(this.security.handleError());
  }

  postUnit(unit: UnitToSave): Observable<any> {
    return this.http.post(`${this.url}`, unit).pipe(this.security.handleError());
  }

  putUnit(id: number, unit: UnitToSave): Observable<any> {
    return this.http.put(`${this.url}/${id}`, unit).pipe(this.security.handleError());
  }

  deleteUnit(id: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`).pipe(this.security.handleError());
  }
}
