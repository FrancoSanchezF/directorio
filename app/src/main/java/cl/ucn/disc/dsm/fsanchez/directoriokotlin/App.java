/*
 * Copyright (c) 2021 Franco Sanchez Flores.
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.fsanchez.directoriokotlin;

import android.app.Application;
import android.content.Context;

import org.acra.ACRA;
import org.acra.BuildConfig;
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
