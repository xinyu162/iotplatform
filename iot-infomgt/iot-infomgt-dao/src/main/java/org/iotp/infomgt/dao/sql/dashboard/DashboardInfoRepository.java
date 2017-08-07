/*******************************************************************************
 * Copyright 2017 osswangxining@163.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
/**
 * Copyright © 2016-2017 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.iotp.infomgt.dao.sql.dashboard;

import org.iotp.infomgt.dao.model.sql.DashboardInfoEntity;
import org.iotp.infomgt.dao.util.SqlDao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Created by Valerii Sosliuk on 5/6/2017.
 */
@SqlDao
public interface DashboardInfoRepository extends CrudRepository<DashboardInfoEntity, String> {

    @Query("SELECT di FROM DashboardInfoEntity di WHERE di.tenantId = :tenantId " +
            "AND LOWER(di.searchText) LIKE LOWER(CONCAT(:searchText, '%')) " +
            "AND di.id > :idOffset ORDER BY di.id")
    List<DashboardInfoEntity> findByTenantId(@Param("tenantId") String tenantId,
                                             @Param("searchText") String searchText,
                                             @Param("idOffset") String idOffset,
                                             Pageable pageable);

    @Query("SELECT di FROM DashboardInfoEntity di WHERE di.tenantId = :tenantId " +
            "AND di.customerId = :customerId AND LOWER(di.searchText) LIKE LOWER(CONCAT(:searchText, '%')) " +
            "AND di.id > :idOffset ORDER BY di.id")
    List<DashboardInfoEntity> findByTenantIdAndCustomerId(@Param("tenantId") String tenantId,
                                                          @Param("customerId") String customerId,
                                                          @Param("searchText") String searchText,
                                                          @Param("idOffset") String idOffset,
                                                          Pageable pageable);
}
