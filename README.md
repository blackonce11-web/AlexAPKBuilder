# Alex APK Builder

Android application project for preparing Android APK builds.

## Current repository state

This repository currently contains the initial Android shell (version 0.1.0) and a GitHub Actions workflow that builds this application itself. It does **not** yet contain the complete cloud-builder interface from the experimental 0.2.0 source.

## Build this app

1. Open the **Actions** tab.
2. Select **Compilar Alex APK Builder**.
3. Choose **Run workflow** if the push did not start a run automatically.
4. Download the `AlexAPKBuilder-debug-apk` artifact after a successful run.

The workflow uses a standard GitHub-hosted Ubuntu runner and only builds a Debug APK.

## Important public-repository warning

This repository is public. Anyone can read its source and files. Do not upload private Android project ZIPs, API tokens, signing keystores, passwords, or customer data here.

A future cloud-build feature must clearly disclose where a selected project ZIP is uploaded. It must not send private source code to this public repository without a separate explicit warning and consent. For private source projects, use a private build workspace or another provider with appropriate privacy controls; private GitHub Actions usage may consume included minutes.

## Current limitations

- The app shell still uses Termux for local Gradle commands.
- ZIP import and cloud compilation from inside the app are not yet implemented in this repository.
- Codemagic, Bitrise, automatic provider fallback, integrated build logs, and secure release signing are not yet implemented.
- Release builds are not guaranteed to be signed or installable as updates.

## Security

Never commit secrets. Use least-privilege credentials and GitHub Actions secrets for future integrations. Treat uploaded Android projects as executable build input because Gradle build scripts can run code.
