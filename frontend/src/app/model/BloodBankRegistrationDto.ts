export class BloodBankRegistrationDto {
    id: string = '';
    title: string = '';
    description: string= '';
    averageGrade: number= 0;
    workingHoursStart : string ='08:00';
    workingHoursEnd : string ='20:00';
    city: string ='';
    street: string ='';
    country: string ='';   
    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.title = obj.title;    
            this.description = obj.description;   
            this.averageGrade = obj.averageGrade;
            this.workingHoursStart = obj.workingHoursStart; 
            this.workingHoursEnd = obj.workingHoursEnd;    
            this.city = obj.city;   
            this.street = obj.street;
            this.country = obj.country;
             
        }
    }
}