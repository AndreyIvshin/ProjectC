import {Component, OnInit} from '@angular/core';
import {ItemService} from '../../../service/item.service';
import {Router} from '@angular/router';
import {ItemToSave} from '../../../model/item/item-to-save';
import {UnitRef} from '../../../model/unit/unit-ref';
import {UnitLite} from '../../../model/unit/unit-lite';
import {UnitService} from '../../../service/unit.service';

@Component({
  selector: 'app-item-add',
  templateUrl: './item-add.component.html',
  styleUrls: ['./item-add.component.css']
})
export class ItemAddComponent implements OnInit {

  constructor(private service: ItemService, private unitService: UnitService, private router: Router) { }

  item = new ItemToSave();
  unit = new UnitRef();
  units: UnitLite[] = [];

  ngOnInit(): void {
    this.unitService.getUnitList().subscribe(value => this.units = value);
  }

  add(): void {
    this.item.unit = this.unit;
    this.service.postItem(this.item).subscribe(value => this.router.navigate(['item-list']));
  }

}
