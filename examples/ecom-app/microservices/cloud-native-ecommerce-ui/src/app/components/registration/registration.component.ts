import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  @ViewChild('wrapper') wrapper: ElementRef;
  validators: Validators[] = [Validators.required, Validators.minLength(4), Validators.maxLength(12)];

  constructor(private renderer: Renderer2, private fb: FormBuilder) { }

  ngOnInit(): void {
    if (sessionStorage.getItem('username') && sessionStorage.getItem('token')) {
      console.log("My username is " + sessionStorage.getItem('username'));
      console.log("My token is " + sessionStorage.getItem('token'));
    } else {
      console.log("This request is not authorized.")
    }
  }

  onSubmit() {
    if (!this.regForm.valid) { return; }
  }

  onToggleOverlay(actionName): void {
    this.renderer[actionName](this.wrapper.nativeElement, 'overlay-active');
  }

  regForm = this.fb.group({
    username: ['', this.validators],
    password: ['', this.validators],
    passwordConfirmation: ['', this.validators]
  }, {validator: this.checkForm})

  checkForm(group: FormGroup) {
    const password1 = group.get('password').value;
    const password2 = group.get('passwordConfirmation').value;
    return password1 === password2 ? null : { notSame: true }
  }

}
