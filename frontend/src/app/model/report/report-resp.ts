import {EntityFull} from '../entity/entity-full';
import {DealFull} from '../deal/deal-full';
import {ReportRespItem} from './report-resp-item';

export class ReportResp {
  first: Date | undefined;
  last: Date | undefined;
  entity: EntityFull | undefined;
  sellDeals: DealFull[] | undefined;
  buyDeals: DealFull[] | undefined;
  sellItems: ReportRespItem[] | undefined;
  buyItems: ReportRespItem[] | undefined;
  sum: number | undefined;
  allSum: number | undefined;
  allItems: ReportRespItem[] | undefined;
}
