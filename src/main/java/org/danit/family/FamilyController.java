package org.danit.family;

import org.apache.commons.lang.NotImplementedException;

public class FamilyController<Request, Response> {
    private final FamilyService service = new FamilyService(new FamilyDao());

    public Response get(Request request) {
        throw new NotImplementedException("Here you can do some staff using access to the service");
    }

    public Response post(Request request) {
        throw new NotImplementedException("Here you can do some staff using access to the service");
    }

    public Response put(Request request) {
        throw new NotImplementedException("Here you can do some staff using access to the service");
    }

    public Response update(Request request) {
        throw new NotImplementedException("Here you can do some staff using access to the service");
    }

    public Response delete(Request request) {
        throw new NotImplementedException("Here you can do some staff using access to the service");
    }
}
