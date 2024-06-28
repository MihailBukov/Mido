import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '@progress/kendo-angular-notification';
import { Client } from 'src/app/models/Client';
import { Role } from 'src/app/models/Role';
import { Status } from 'src/app/models/Status';
import { User } from 'src/app/models/User';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit{
  clientForm = this.fb.group({
    firstName: ['', Validators.required],
    middleName: ['', Validators.required],
    lastName: ['', Validators.required],
    age: [0, Validators.required],
    country: ['', Validators.required],
    city: ['', Validators.required],
    photo: [''],
    description: [''],
    user: this.fb.group({
      username: ['', Validators.required],
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required, Validators.min(5)],
      role: [Role.CLIENT, Validators.required],
      status: [Status.OFFLINE, Validators.required]
    })
  });

  constructor(private fb: FormBuilder, private authService: AuthService,
    private router: Router, private route: ActivatedRoute, private notificationService: NotificationService) {}

  ngOnInit(): void {
    this.authService.getUserByUsername(this.route.snapshot.params['username']).subscribe((user: User) => {
      this.clientForm.get('user')?.patchValue(user);
    });
  }

  createClient() {
    const client = { ... this.clientForm.value };
    this.authService.createClient(client as Client).subscribe(
      response => {
        this.notificationService.show({
          content: 'Client has been created',
          type: { style: 'success', icon: true },
          animation: { type: 'slide', duration: 600 },
          position: { horizontal: 'center', vertical: 'bottom'},
          closable: true
        });
        this.router.navigate(['home']);
      },
      error => {
        this.notificationService.show({
          content: 'There was an error while creating the client',
          type: { style: 'error', icon: true },
          animation: { type: 'slide', duration: 600 },
          position: { horizontal: 'center', vertical: 'bottom'},
          closable: true
        });
      }
    )
  }
}
