package com.plaid.quickstart.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plaid.quickstart.core.User;
import io.dropwizard.auth.Auth;
import io.dropwizard.views.View;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class IndexResource {
  private final String plaidEnvironment;
  private final String plaidPublicKey;

  public IndexResource(String plaidEnvironment, String plaidPublicKey) {
    this.plaidEnvironment = plaidEnvironment;
    this.plaidPublicKey = plaidPublicKey;
  }

  @GET
  public Response get(@Auth User user) {
    return Response.ok(new IndexView(plaidEnvironment, plaidPublicKey)).build();
  }

  public class IndexView extends View {
    @JsonProperty
    private final String plaidEnvironment;

    @JsonProperty
    private final String plaidPublicKey;

    public IndexView(String plaidEnvironment, String plaidPublicKey) {
      super("../../../../templates/index.ftl");
      this.plaidEnvironment = plaidEnvironment;
      this.plaidPublicKey = plaidPublicKey;
    }

    public String getPlaidEnvironment() {
      return plaidEnvironment;
    }

    public String getPlaidPublicKey() {
      return plaidPublicKey;
    }
  }
}
