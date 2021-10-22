package cl.ucn.disc.dsm.fsanchez.directoriokotlin;

import android.app.Application;
import android.content.Context;

import org.acra.ACRA;
import org.acra.BuildConfig;
import org.acra.annotation.AcraCore;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.config.DialogConfigurationBuilder;
import org.acra.config.MailSenderConfigurationBuilder;
import org.acra.data.StringFormat;

/**
 * Main App.
 * @author Franco Sanchez-Flores
 */
public class App extends Application{
  /**
   *
   * @param base context to use.
   */
  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);

    // ACRA Configuration
    CoreConfigurationBuilder builder = new CoreConfigurationBuilder(this);
    builder
            .withBuildConfigClass(BuildConfig.class)
            .withReportFormat(StringFormat.JSON)
            .withEnabled(true);

    // ACRA Dialog Configuration
    builder.getPluginConfigurationBuilder(DialogConfigurationBuilder.class)
            .withResText(R.string.acra_dialog_title)
            .withCommentPrompt(String.valueOf(R.string.acra_dialog_comment))
            .withEnabled(true);

    // ACRA Email Configuration
    builder.getPluginConfigurationBuilder(MailSenderConfigurationBuilder.class)
            .withMailTo("franco.sanchez@alumnos.ucn.cl")
            .withReportFileName("crash.txt")
            .withSubject(getString(R.string.acra_dialog_title))
            .withBody(getString(R.string.acra_dialog_comment))
            .withEnabled(true);

    // The following line triggers the inicialization of ACRA
    ACRA.init(this, builder);
  }
}
