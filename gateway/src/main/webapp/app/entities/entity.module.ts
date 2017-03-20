import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { GatewayCountryMySuffixModule } from './country/country-my-suffix.module';
import { GatewayDepartmentMySuffixModule } from './department/department-my-suffix.module';
import { GatewayEmployeeMySuffixModule } from './employee/employee-my-suffix.module';
import { GatewayJobMySuffixModule } from './job/job-my-suffix.module';
import { GatewayJobHistoryMySuffixModule } from './job-history/job-history-my-suffix.module';
import { GatewayLocationMySuffixModule } from './location/location-my-suffix.module';
import { GatewayRegionMySuffixModule } from './region/region-my-suffix.module';
import { GatewayTaskMySuffixModule } from './task/task-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        GatewayCountryMySuffixModule,
        GatewayDepartmentMySuffixModule,
        GatewayEmployeeMySuffixModule,
        GatewayJobMySuffixModule,
        GatewayJobHistoryMySuffixModule,
        GatewayLocationMySuffixModule,
        GatewayRegionMySuffixModule,
        GatewayTaskMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayEntityModule {}
