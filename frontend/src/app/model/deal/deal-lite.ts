import {ItemLite} from '../item/item-lite';
import {EntityLite} from '../entity/entity-lite';

export class DealLite {
  id: number | undefined;
  date: Date | undefined;
  price: number | undefined;
  quantity: number | undefined;
  item: ItemLite | undefined;
  seller: EntityLite | undefined;
  buyer: EntityLite | undefined;
}
