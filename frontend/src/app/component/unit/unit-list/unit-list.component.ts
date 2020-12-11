import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UnitLite} from '../../../model/unit/unit-lite';
import {UnitService} from '../../../service/unit.service';

@Component({
  selector: 'app-unit-list',
  templateUrl: './unit-list.component.html',
  styleUrls: ['./unit-list.component.css']
})
export class UnitListComponent implements OnInit {

  constructor(private service: UnitService, private router: Router) {
  }

  units: UnitLite[] = [];

  ngOnInit(): void {
    this.service.getUnitList().subscribe(value => this.units = value);
  }

  remove(id: number): void {
    this.service.deleteUnit(id).subscribe(value => this.router.navigate(['unit-list']));
  }

}
