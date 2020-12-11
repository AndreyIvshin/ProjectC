import {EntityRef} from '../entity/entity-ref';

export class ReportReq {
  first: Date | undefined;
  last: Date | undefined;
  entity: EntityRef | undefined;
}
