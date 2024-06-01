package com.mercadolibre.rampup_juan_castano.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String STATUS_EDIBLE = "comestible";
    public static final String STATUS_ROTTEN = "podrido";

    //datadog metrics
    public static final String NAME_DD_FRUIT_METRIC="rampup.onboarding.final-cross-be-rampup-esp-w0018-team13.fruit";
    public static final String ACTION_SAVE="action:save";
    public static final String ACTION_GET = "action:get";
    public static final String ACTION_PUT = "action:put";
    public static final String ACTION_DELETE = "action:delete";

    //kvs configuration
    public static final int SOCKET_TIMEOUT = 150;
    public static final int MAX_WAIT = 100;
    public static final int CONNECTION_TIMEOUT = 100;
    public static final int MAX_CONNECTIONS = 30;
    public static final int MAX_CONNECTIONS_PER_ROUTE = 30;
    public static final int RETRY_DELAY = 30;
    public static final int MAX_RETRIES = 1;
    public static final int KVS_TTL = 36000;
}
