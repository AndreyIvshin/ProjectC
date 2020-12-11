import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {DealToSave} from '../../../model/deal/deal-to-save';
import {DealService} from '../../../service/deal.service';
import {ItemService} from '../../../service/item.service';
import {UnitService} from '../../../service/unit.service';
import {EntityService} from '../../../service/entity.service';
import {ItemLite} from '../../../model/item/item-lite';
import {UnitLite} from '../../../model/unit/unit-lite';
import {EntityLite} from '../../../model/entity/entity-lite';
import {UnitRef} from '../../../model/unit/unit-ref';
import {ItemRef} from '../../../model/item/item-ref';
import {EntityRef} from '../../../model/entity/entity-ref';
import {DealFull} from "../../../model/deal/deal-full";

@Component({
  selector: 'app-deal-write',
  templateUrl: './deal-write.component.html',
  styleUrls: ['./deal-write.component.css']
})
export class DealWriteComponent implements OnInit {

  constructor(private service: DealService, private itemService: ItemService,
              private unitService: UnitService, private entityService: EntityService,
              private router: Router, private activatedRoute: ActivatedRoute) { }

  private id = 0;

  deal = new DealToSave();
  item = new ItemRef();
  seller = new EntityRef();
  buyer = new EntityRef();
  items: ItemLite[] = [];
  entities: EntityLite[] = [];

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((map: ParamMap) => this.id = parseInt(map.get('id') || '0', 10));
    this.itemService.getItemList().subscribe(value => this.items = value);
    this.entityService.getEntityList().subscribe(value => this.entities = value);
    this.service.getDeal(this.id).subscribe(value => {
      this.deal.date = value.date;
      this.deal.price = value.price;
      this.deal.quantity = value.quantity;
      this.item.id = value.item?.id;
      this.seller.id = value.seller?.id;
      this.buyer.id = value.buyer?.id;
    });
  }

  write(): void {
    this.deal.item = this.item;
    this.deal.seller = this.seller;
    this.deal.buyer = this.buyer;
    this.service.putDeal(this.id, this.deal).subscribe(value => this.router.navigate(['deal-list']));
  }

  remove(): void {
    this.service.deleteDeal(this.id).subscribe(value => this.router.navigate(['deal-list']));
  }

  sellerIsBuyer(): boolean {
    return this.buyer.id === this.seller.id && this.buyer.id !== undefined;
  }

}
