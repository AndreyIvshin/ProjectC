import {ItemRef} from '../item/item-ref';
import {EntityRef} from '../entity/entity-ref';

export class DealToSave {
  date: Date | undefined;
  price: number | undefined;
  quantity: number | undefined;
  item: ItemRef | undefined;
  seller: EntityRef | undefined;
  buyer: EntityRef | undefined;
}
