import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {DealService} from '../../../service/deal.service';
import {DealToSave} from '../../../model/deal/deal-to-save';
import {ItemLite} from '../../../model/item/item-lite';
import {UnitLite} from '../../../model/unit/unit-lite';
import {EntityLite} from '../../../model/entity/entity-lite';
import {ItemService} from '../../../service/item.service';
import {UnitService} from '../../../service/unit.service';
import {EntityService} from '../../../service/entity.service';
import {UnitRef} from '../../../model/unit/unit-ref';
import {ItemRef} from '../../../model/item/item-ref';
import {EntityRef} from '../../../model/entity/entity-ref';

@Component({
  selector: 'app-deal-add',
  templateUrl: './deal-add.component.html',
  styleUrls: ['./deal-add.component.css']
})
export class DealAddComponent implements OnInit {

  constructor(private service: DealService, private itemService: ItemService,
              private unitService: UnitService, private entityService: EntityService,
              private router: Router) { }

  deal = new DealToSave();
  unit = new UnitRef();
  item = new ItemRef();
  seller = new EntityRef();
  buyer = new EntityRef();
  items: ItemLite[] = [];
  entities: EntityLite[] = [];

  ngOnInit(): void {
    this.deal.date = new Date();
    this.deal.price = 1;
    this.deal.quantity = 1;
    this.itemService.getItemList().subscribe(value => this.items = value);
    this.entityService.getEntityList().subscribe(value => this.entities = value);
  }

  add(): void {
    this.deal.item = this.item;
    this.deal.seller = this.seller;
    this.deal.buyer = this.buyer;
    this.service.postDeal(this.deal).subscribe(value => this.router.navigate(['deal-list']));
  }

  sellerIsBuyer(): boolean {
    return this.buyer.id === this.seller.id && this.buyer.id !== undefined;
  }

}
