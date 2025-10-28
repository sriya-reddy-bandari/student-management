import { Component } from '@angular/core';
import { RouterOutlet,Router } from '@angular/router';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  constructor(private readonly router: Router) {}

  navigateTo(path: string) {
    this.router.navigate([path]);
}
}
