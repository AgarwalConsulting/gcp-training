import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @ViewChild('wrapper') wrapper: ElementRef;
  validators: Validators[] = [Validators.required, Validators.minLength(4), Validators.maxLength(12)];

  constructor(private renderer: Renderer2, private fb: FormBuilder) { }

  ngOnInit(): void {
  }

  onToggleOverlay(actionName): void {
    this.renderer[actionName](this.wrapper.nativeElement, 'overlay-active');
  }

  onSubmit(): void{
    if (!this.loginForm.valid) { return; }
  }

  loginForm = this.fb.group({
    username: ['', this.validators],
    password: ['', this.validators]
  })

}
