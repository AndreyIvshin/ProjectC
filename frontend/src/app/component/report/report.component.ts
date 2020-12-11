import {Component, OnInit} from '@angular/core';
import {EntityService} from '../../service/entity.service';
import {Router} from '@angular/router';
import {EntityRef} from '../../model/entity/entity-ref';
import {EntityLite} from '../../model/entity/entity-lite';
import {ReportReq} from '../../model/report/report-req';
import {ReportService} from '../../service/report.service';
import {ReportResp} from '../../model/report/report-resp';
import * as FileSaver from "file-saver";
import {EntityFull} from "../../model/entity/entity-full";

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  constructor(private service: ReportService, private entityService: EntityService, private router: Router) { }

  req = new ReportReq();
  entity = new EntityRef();
  entities: EntityLite[] = [];
  ready = false;
  resp = new ReportResp();

  ngOnInit(): void {
    this.req.first = new Date();
    this.req.last = new Date();
    this.entityService.getEntityList().subscribe(value => this.entities = value);
  }

  gen(): void {
    this.req.entity = this.entity;
    this.service.getReport(this.req).subscribe(value => {
      this.resp = value;
      this.ready = true;
    });
  }

  print(): void {
    this.req.entity = this.entity;
    this.service.print(this.req).subscribe(value => {
      const blob = new Blob([value], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=utf-8'});
      FileSaver.saveAs(blob, 'report.xlsx');
    });
  }

}

class Transaction {
  constructor(public quantity: number, public unit: string, public item: string, public price: number) {
  }
}
