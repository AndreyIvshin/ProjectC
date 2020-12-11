import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SecurityService} from './security.service';
import {Observable} from 'rxjs';
import {EntityLite} from '../model/entity/entity-lite';
import {EntityFull} from '../model/entity/entity-full';
import {EntityToSave} from '../model/entity/entity-to-save';


@Injectable({
  providedIn: 'root'
})
export class EntityService {

  constructor(private http: HttpClient, private security: SecurityService) { }

  private url = `${SecurityService.base}/entities`;

  getEntityList(): Observable<EntityLite[]> {
    return this.http.get(`${this.url}`).pipe(this.security.handleError());
  }

  getEntity(id: number): Observable<EntityFull> {
    return this.http.get(`${this.url}/${id}`).pipe(this.security.handleError());
  }

  postEntity(item: EntityToSave): Observable<any> {
    return this.http.post(`${this.url}`, item).pipe(this.security.handleError());
  }

  putEntity(id: number, item: EntityToSave): Observable<any> {
    return this.http.put(`${this.url}/${id}`, item).pipe(this.security.handleError());
  }

  deleteEntity(id: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`).pipe(this.security.handleError());
  }
}
