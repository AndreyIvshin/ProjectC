import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {EntityService} from '../../../service/entity.service';
import {EntityToSave} from '../../../model/entity/entity-to-save';
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-entity-write',
  templateUrl: './entity-write.component.html',
  styleUrls: ['./entity-write.component.css']
})
export class EntityWriteComponent implements OnInit {

  constructor(private service: EntityService, private router: Router, private activatedRoute: ActivatedRoute) { }

  private id = 0;

  entity = new EntityToSave();
  canBeRemoved = true;

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((map: ParamMap) => this.id = parseInt(map.get('id') || '0', 10));
    this.service.getEntity(this.id).subscribe(value => {
      this.entity.name = value.name;
    });
  }

  remove(): void {
    this.service.deleteEntity(this.id).subscribe(value => this.router.navigate(['entity-list']),
      error => this.canBeRemoved = new HttpErrorResponse(error).status !== 403);
  }

  write(): void {
    this.service.putEntity(this.id, this.entity).subscribe(value => this.router.navigate(['entity-list']));
  }
}
