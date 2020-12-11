import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {UnitService} from '../../../service/unit.service';
import {UnitToSave} from '../../../model/unit/unit-to-save';
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-unit-write',
  templateUrl: './unit-write.component.html',
  styleUrls: ['./unit-write.component.css']
})
export class UnitWriteComponent implements OnInit {

  constructor(private service: UnitService, private router: Router, private activatedRoute: ActivatedRoute) {
  }

  private id = 0;

  unit = new UnitToSave();
  canBeRemoved = true;

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((map: ParamMap) => this.id = parseInt(map.get('id') || '0', 10));
    this.service.getUnit(this.id).subscribe(value => {
      this.unit.name = value.name;
    });
  }

  remove(): void {
    console.log('?');
    this.service.deleteUnit(this.id).subscribe(value => this.router.navigate(['unit-list']),
      error => this.canBeRemoved = new HttpErrorResponse(error).status !== 403);
  }

  write(): void {
    this.service.putUnit(this.id, this.unit).subscribe(value => this.router.navigate(['unit-list']));
  }
}
