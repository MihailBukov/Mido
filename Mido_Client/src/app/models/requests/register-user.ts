export interface RegisterUserRequest {
  username: string;
  email: string;
  password: string;
  role: 'CLIENT' | 'PET_SHELTER';
  firstName?: string;
  middleName?: string;
  lastName?: string;
  age?: number;
  country?: string;
  city?: string;
  description?: string;
  name?: string;
  capacity?: number;
  address?: string;
}
