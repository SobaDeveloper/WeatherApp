package soba.weatherapp.di.component;

import dagger.Component;
import javax.inject.Singleton;
import soba.weatherapp.di.module.AppModule;
import soba.weatherapp.di.module.NetworkModule;
import soba.weatherapp.di.module.RepositoryModule;
import soba.weatherapp.di.module.ServiceModule;
import soba.weatherapp.presentation.view.main.MainActivity;

/**
 * Created by Levi on 3/23/18.
 */

@Singleton @Component(modules = {
    AppModule.class, NetworkModule.class, ServiceModule.class, RepositoryModule.class
}) public interface MainComponent {

    void inject(MainActivity mainActivity);
}
