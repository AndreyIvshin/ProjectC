import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SecurityService} from './security.service';
import {Observable} from 'rxjs';
import {ReportResp} from '../model/report/report-resp';
import {ReportReq} from '../model/report/report-req';

@Injectable({
  providedIn: 'root'
})
export class ReportService {


  constructor(private http: HttpClient, private security: SecurityService) { }

  private url = `${SecurityService.base}/reports`;

  getReport(req: ReportReq): Observable<ReportResp> {
    return this.http.post(`${this.url}`, req).pipe(this.security.handleError());
  }

  print(req: ReportReq): Observable<any> {
    return this.http.post(`${this.url}/print`, req, {responseType: 'blob'}).pipe(this.security.handleError());
  }

}
