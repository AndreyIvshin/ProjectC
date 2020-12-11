import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SecurityService} from './security.service';
import {ItemLite} from '../model/item/item-lite';
import {ItemFull} from '../model/item/item-full';
import {ItemToSave} from '../model/item/item-to-save';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient, private security: SecurityService) { }

  private url = `${SecurityService.base}/items`;

  getItemList(): Observable<ItemLite[]> {
    return this.http.get(`${this.url}`).pipe(this.security.handleError());
  }

  getItem(id: number): Observable<ItemFull> {
    return this.http.get(`${this.url}/${id}`).pipe(this.security.handleError());
  }

  postItem(item: ItemToSave): Observable<any> {
    return this.http.post(`${this.url}`, item).pipe(this.security.handleError());
  }

  putItem(id: number, item: ItemToSave): Observable<any> {
    return this.http.put(`${this.url}/${id}`, item).pipe(this.security.handleError());
  }

  deleteItem(id: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`).pipe(this.security.handleError());
  }

}
