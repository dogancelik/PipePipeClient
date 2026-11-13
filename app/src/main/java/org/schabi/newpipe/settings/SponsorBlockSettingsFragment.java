package org.schabi.newpipe.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.Preference;

import org.schabi.newpipe.R;
import org.schabi.newpipe.util.ServiceHelper;

public class SponsorBlockSettingsFragment extends BasePreferenceFragment {
    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(final Bundle savedInstanceState, final String rootKey) {
        addPreferencesFromResourceRegistry();

        final Preference apiUrlPreference =
                findPreference(getString(R.string.sponsor_block_custom_api_url_key));
        assert apiUrlPreference != null;
        apiUrlPreference.setOnPreferenceChangeListener((p, newValue) -> {
            // the preference saves the value before notifying us, so this is safe to call now
            ServiceHelper.initServices(requireContext());
            return true;
        });

        final Preference sponsorBlockWebsitePreference =
                findPreference(getString(R.string.sponsor_block_home_page_key));
        assert sponsorBlockWebsitePreference != null;
        sponsorBlockWebsitePreference.setOnPreferenceClickListener((Preference p) -> {
            final Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.sponsor_block_homepage_url)));
            startActivity(i);
            return true;
        });

        final Preference sponsorBlockPrivacyPreference =
                findPreference(getString(R.string.sponsor_block_privacy_key));
        assert sponsorBlockPrivacyPreference != null;
        sponsorBlockPrivacyPreference.setOnPreferenceClickListener((Preference p) -> {
            final Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.sponsor_block_privacy_policy_url)));
            startActivity(i);
            return true;
        });
    }
}
