/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.example.encrypt.table.raw.jdbc;

import org.apache.shardingsphere.example.common.jdbc.repository.UserRepositoryImpl;
import org.apache.shardingsphere.example.common.jdbc.service.UserServiceImpl;
import org.apache.shardingsphere.example.common.service.CommonService;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlEncryptDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class YamlConfigurationExample {

    public static void main(final String[] args) throws SQLException, IOException {
        DataSource dataSource = YamlEncryptDataSourceFactory.createDataSource(getFile());
        CommonService userService = getUserService(dataSource);
        userService.initEnvironment();
        userService.processSuccess();
        userService.cleanEnvironment();
    }
    
    private static File getFile() {
        return new File(Thread.currentThread().getClass().getResource("/META-INF/sharding-databases.yaml").getFile());
    }

    private static CommonService getUserService(final DataSource dataSource) {
        return new UserServiceImpl(new UserRepositoryImpl(dataSource));
    }
}
