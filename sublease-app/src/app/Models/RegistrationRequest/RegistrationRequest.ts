export class RegistrationRequest {
  firstName: string;
  lastName: string;
  email: string;
  private password: string;
  getPassword(): string {
    return this.password;
  }
  setPassword(password: string) {
    this.password = password;
  }
}
