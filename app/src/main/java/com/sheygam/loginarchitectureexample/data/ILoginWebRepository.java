package com.sheygam.loginarchitectureexample.data;

/**
 * Created by gregorysheygam on 27/12/2017.
 */

public interface ILoginWebRepository {
    void registration(String email, String password, ILoginWebRepositoryCallback callback);
    void login(String email, String password, ILoginWebRepositoryCallback callback);
}
