package com.insurance.www;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;

public class CustomerIdGenerator implements IdentifierGenerator {

    private static final String PREFIX = "CUS";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {

        String sql = "SELECT cust_id FROM customer ORDER BY cust_id DESC LIMIT 1";

        String lastId = (String) session
                .createNativeQuery(sql)
                .uniqueResult();

        int nextNumber = 1;

        if (lastId != null) {
            nextNumber = Integer.parseInt(lastId.substring(3)) + 1;
        }

        return PREFIX + String.format("%07d", nextNumber);
    }
}
