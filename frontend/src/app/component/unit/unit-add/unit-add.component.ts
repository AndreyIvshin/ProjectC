import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UnitService} from '../../../service/unit.service';
import {UnitToSave} from '../../../model/unit/unit-to-save';

@Component({
  selector: 'app-unit-add',
  templateUrl: './unit-add.component.html',
  styleUrls: ['./unit-add.component.css']
})
export class UnitAddComponent implements OnInit {

  constructor(private service: UnitService, private router: Router) { }

  unit = new UnitToSave();

  ngOnInit(): void {
  }

  add(): void {
    this.service.postUnit(this.unit).subscribe(value => this.router.navigate(['unit-list']));
  }

}
