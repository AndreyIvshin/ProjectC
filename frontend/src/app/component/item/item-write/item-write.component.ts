import {Component, OnInit} from '@angular/core';
import {ItemService} from '../../../service/item.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {ItemToSave} from '../../../model/item/item-to-save';
import {UnitRef} from '../../../model/unit/unit-ref';
import {UnitLite} from '../../../model/unit/unit-lite';
import {UnitService} from '../../../service/unit.service';
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-item-write',
  templateUrl: './item-write.component.html',
  styleUrls: ['./item-write.component.css']
})
export class ItemWriteComponent implements OnInit {

  constructor(private service: ItemService, private unitService: UnitService,
              private router: Router, private activatedRoute: ActivatedRoute) { }

  private id = 0;

  item = new ItemToSave();
  unit = new UnitRef();
  units: UnitLite[] = [];
  canBeRemoved = true;

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((map: ParamMap) => this.id = parseInt(map.get('id') || '0', 10));
    this.unitService.getUnitList().subscribe(value => this.units = value);
    this.service.getItem(this.id).subscribe(value => {
      this.item.name = value.name;
      this.unit.id = value.unit?.id;
    });
  }

  remove(): void {
    this.service.deleteItem(this.id).subscribe(value => this.router.navigate(['item-list']),
      error => this.canBeRemoved = new HttpErrorResponse(error).status !== 403);
  }

  write(): void {
    this.item.unit = this.unit;
    this.service.putItem(this.id, this.item).subscribe(value => this.router.navigate(['item-list']));
  }
}
