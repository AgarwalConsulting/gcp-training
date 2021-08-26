import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomepageComponent} from "./components/homepage/homepage.component";
import {CartComponent} from "./components/cart/cart.component";
import {CheckoutComponent} from "./components/checkout/checkout.component";
import {LoginComponent} from "./components/login/login.component";
import {ProductDetailsComponent} from "./components/product-details/product-details.component";
import {RegistrationComponent} from "./components/registration/registration.component";

const routes: Routes = [
  { path: 'cart', component: CartComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'index', component: HomepageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'product-details/:productId', component: ProductDetailsComponent },
  { path: 'registration', component: RegistrationComponent },
  // TODO: PageNotFound component
  { path: '**', component: HomepageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [CartComponent, CheckoutComponent, HomepageComponent,
  LoginComponent, ProductDetailsComponent, RegistrationComponent]
