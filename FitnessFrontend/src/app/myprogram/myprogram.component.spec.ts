import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyprogramComponent } from './myprogram.component';

describe('MyprogramComponent', () => {
  let component: MyprogramComponent;
  let fixture: ComponentFixture<MyprogramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MyprogramComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MyprogramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
