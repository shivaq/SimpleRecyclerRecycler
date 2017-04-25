package yasuaki.kyoto.com.simplerecyclerrecycler.di.component;

import dagger.Component;
import yasuaki.kyoto.com.simplerecyclerrecycler.di.PerActivity;
import yasuaki.kyoto.com.simplerecyclerrecycler.di.module.ActivityModule;
import yasuaki.kyoto.com.simplerecyclerrecycler.ui.MainActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
}