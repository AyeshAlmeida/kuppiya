/*
 * (C) Copyright 2010- 2019 hSenid Mobile Solutions (Pvt) Limited.
 * All Rights Reserved.
 *
 * These materials are unpublished, proprietary, confidential source code of
 * hSenid Mobile Solutions (Pvt) Limited and constitute a TRADE SECRET
 * of hSenid Mobile Solutions (Pvt) Limited.
 *
 * hSenid Mobile Solutions (Pvt) Limited retains all title to and intellectual
 * property rights in these materials.
 */

package hms.cpaas.kuppiya.service.config;

import java.util.Optional;

public interface ConfigurationLoader<T extends ConfigurationData> {

    /**
     * Load the configuration from file or db
     * @return
     */
    Optional<T> loadData();

    /**
     * Type Code of the configuration
     * @return
     */
    String getConfigType();
}
