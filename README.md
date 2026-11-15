# Tenant Management System

**Course:** BBT 3.2 Mobile Application Development (BBT3202)  
**Institution:** Strathmore University -- School of Computing and Engineering Sciences  
**Repository:** [rtheuri/Tenant_Management-System-2](https://github.com/rtheuri/Tenant_Management-System-2)  
**Default Branch:** `master`  
**Current Status:** Stages 1-5 Merged; Stage 6 (Release & Documentation) In Progress (PR #7)

---

## 1. Project Title and Status

The Tenant Management System is a native Android mobile application developed in Kotlin. It is created to assist property owners and landlords in managing rental property records, monitoring tenant numbers, calculating total monthly rental income, and communicating directly with occupants through mobile device integration.

### Current Implementation Status
- Stage 1 (Architecture & Folder Organization): Completed and merged into `master` ([PR #3](https://github.com/rtheuri/Tenant_Management-System-2/pull/3)).
- Stage 2 (ViewModel & LiveData): Completed and merged into `master` ([PR #1](https://github.com/rtheuri/Tenant_Management-System-2/pull/1)).
- Stage 3 (Room Database): Completed and merged into `master` ([PR #6](https://github.com/rtheuri/Tenant_Management-System-2/pull/6)).
- Stage 4 & 4B (Screens Integration): Completed and merged into `master` ([PR #2](https://github.com/rtheuri/Tenant_Management-System-2/pull/2) and [PR #4](https://github.com/rtheuri/Tenant_Management-System-2/pull/4)).
- Stage 5 (Data Integration): Completed and merged into `master` ([PR #5](https://github.com/rtheuri/Tenant_Management-System-2/pull/5)).
- Stage 6 (Release and Documentation): In progress (Pull Request #7 open, awaiting review and release build).

---

## 2. Project Overview

The Tenant Management System provides landlords with a mobile application to maintain records of tenants residing in their rental properties. Landlords can log into their account, view an overview dashboard displaying active tenant counts and aggregate monthly rent, register new tenants through an input form, review a roster of active tenants, and initiate phone calls directly from the application interface.

The application is structured following modern Android development practices, emphasizing clean separation between user interface components and data management logic, lifecycle-aware state preservation, and collaborative Git workflow across modular feature branches.

> **Technical Notice on Current Authentication and Storage:**  
> The registration and login interfaces demonstrate client-side input validation, intent navigation, and user session information passing via Intent extras. They do not connect to a remote authentication server or provide encrypted credential storage. Tenant records are permanently persisted in an embedded SQLite database using Android Room.

---

## 3. Purpose and Objectives

Manual tracking of tenants using paper files or generic spreadsheets often causes disorganized contact records, computational errors in rent aggregation, and slow communication. The Tenant Management System addresses these challenges through the following objectives:

1. **User Navigation & Onboarding:** Provide registration and login screens with input validation and clean backstack navigation.
2. **Real-Time Property Dashboard:** Provide an immediate statistical summary of the property, displaying total registered occupants and aggregate monthly rent collected.
3. **Tenant Registration & Validation:** Enable entry of tenant details (name, phone number, rent amount) with validation to prevent blank submissions.
4. **Tenant Communication Integration:** Integrate one-touch phone dialer actions using Android Implicit Intents (`Intent.ACTION_DIAL`) to call tenants directly.
5. **Architectural Separation of Concerns:** Enforce a layered architecture where presentation logic observes data without directly managing data persistence.
6. **Configuration State Preservation:** Preserve active screen data during device configuration changes (such as device rotation) using Jetpack ViewModels.
7. **Local Offline Persistence:** Persist tenant records across application restarts using an embedded SQLite database managed by Android Room.

---

## 4. Implemented and Pending Features

### Implemented Features (Verified on `master` and `release/v1.0`)
- **Authentication Screens:**
  - `RegisterActivity`: Collects full name, email, and password; validates that all fields are non-empty; passes email to login via explicit intent.
  - `LoginActivity`: Validates credentials; passes user email to `MainActivity`; provides an implicit intent link opening Strathmore University web resources.
- **Application Shell & Navigation:**
  - `MainActivity`: Serves as the navigation host (`NavHostFragment`) and coordinates the top-level Toolbar.
  - **Navigation Drawer:** Synchronized with the Jetpack Navigation component (`nav_graph.xml`); displays the signed-in user's email in the drawer header.
  - **Options Menu:** Provides a "Logout" action that clears the task stack and redirects to `LoginActivity`.
- **Dashboard Screen (`DashboardFragment`):**
  - Utilizes Android Data Binding to reflect active tenant count and formatted total rent (`KSh`).
  - Action buttons routing to Add Tenant and View Tenants screens.
- **Add Tenant Screen (`AddTenantFragment`):**
  - Input fields for tenant name, telephone number, and rent amount.
  - Input validation rejecting incomplete forms with informative user feedback toasts.
  - Safe Args integration passing newly registered tenant objects to the view screen.
- **View Tenants Screen (`ViewTenantsFragment`):**
  - Highlight card displaying the most recently added tenant summary.
  - One-touch "CALL TENANT" button launching the device phone dialer with pre-filled phone details.
  - Dynamic LiveData observer displaying all registered tenants.
- **State Management & Persistence (Stages 2, 3 & 5):**
  - `TenantViewModel`: Extends `AndroidViewModel`, preserves UI state across screen rotation, and launches coroutines for asynchronous database writes.
  - Room Database: Configured via `TenantDatabase` singleton with `@Entity(tableName = "tenants")` and `TenantDao`.
  - Repository Pattern: `TenantRepository` delegates calls directly to `TenantDao`.
  - Permanent persistence: Tenant records survive application restarts and process terminations.

### Pending Features (Stage 6 Release)
- **Final Release Verification & Publication:**
  - Review and approval of Pull Request #7 by Project Lead.
  - Generation of signed release APK (`app-release.apk`) in Android Studio.
  - Publication of GitHub Release tagged `v1.0` with the release APK attached.

---

## 5. Technology Stack

The technology stack is based on the verified build configuration files (`build.gradle.kts` and `gradle/libs.versions.toml`):

- **Programming Language:** Kotlin
- **Build System:** Gradle (Wrapper version `9.6.0`, Android Gradle Plugin `9.4.1`)
- **Compile SDK:** 37
- **Target SDK:** 37
- **Minimum SDK:** 24 (Android 7.0 Nougat)
- **Java Compatibility:** Java 11 (`sourceCompatibility` and `targetCompatibility = JavaVersion.VERSION_11`)
- **UI Framework & Design:**
  - AndroidX AppCompat (`1.8.0`)
  - Google Material Components for Android (`com.google.android.material:material`): The project currently has both a version-catalog declaration (`libs.material` at `1.14.0`) and an explicit dependency declaration (`1.12.0`) in `app/build.gradle.kts`. This is a configuration inconsistency requiring cleanup in the build scripts.
  - ConstraintLayout (`2.1.4`)
  - View Binding and Data Binding (`buildFeatures { viewBinding = true; dataBinding = true }`)
- **Jetpack Architecture & Room Components:**
  - Navigation Component (`androidx.navigation:navigation-fragment-ktx:2.9.8`, `navigation-ui-ktx:2.9.8`)
  - Navigation Safe Args Kotlin Plugin (`2.9.8`)
  - Lifecycle ViewModel KTX (`androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7`)
  - Lifecycle LiveData KTX (`androidx.lifecycle:lifecycle-livedata-ktx:2.8.7`)
  - Activity KTX (`1.8.0`)
  - Google KSP Plugin (`com.google.devtools.ksp:2.3.6`)
  - Android Room Runtime & KTX (`androidx.room:room-runtime:2.8.5`, `androidx.room:room-ktx:2.8.5`)
  - Room Compiler (`androidx.room:room-compiler:2.8.5`)

---

## 6. Architecture and Package Structure

The codebase adheres to the layered architecture introduced in Stage 1, enforcing separation of concerns between presentation and data operations. The UI layer communicates with the data layer via the ViewModel and Repository, while the data layer maintains no dependencies on UI classes.

### Implemented Package Structure (`release/v1.0`)

```text
app/src/main/java/com/example/tenantmanagementsystem2/
├── data/
│   ├── local/
│   │   ├── TenantDao.kt                  # Room Data Access Object interface
│   │   └── TenantDatabase.kt             # Room database configuration singleton
│   ├── model/
│   │   └── Tenant.kt                     # Room Entity and domain model (Serializable)
│   └── repository/
│       └── TenantRepository.kt           # Central data repository connecting to TenantDao
└── ui/
    ├── auth/
    │   ├── LoginActivity.kt              # Login screen and authentication handling
    │   └── RegisterActivity.kt           # Account registration screen
    ├── dashboard/
    │   └── DashboardFragment.kt          # Dashboard displaying tenant statistics
    ├── main/
    │   └── MainActivity.kt               # Navigation container, Toolbar, and Drawer host
    ├── tenants/
    │   ├── AddTenantFragment.kt          # Tenant entry form with input validation
    │   └── ViewTenantsFragment.kt        # Tenant roster display and dialer intent trigger
    └── viewmodel/
        ├── .gitkeep
        └── TenantViewModel.kt            # AndroidViewModel coordinating UI state and Room
```

---

## 7. Core Component Responsibilities

| Component | Layer | Primary Responsibility |
| :--- | :--- | :--- |
| `LoginActivity` | UI (`ui.auth`) | Validates landlord credentials, provides registration links, and navigates to `MainActivity`. |
| `RegisterActivity` | UI (`ui.auth`) | Accepts new user details, validates form input, and returns email to `LoginActivity`. |
| `MainActivity` | UI (`ui.main`) | Acts as the primary UI shell, hosts `NavHostFragment`, syncs drawer navigation, and manages Logout. |
| `DashboardFragment` | UI (`ui.dashboard`) | Connects to `TenantViewModel` via Data Binding to display active tenant counts and aggregate monthly rent. |
| `AddTenantFragment` | UI (`ui.tenants`) | Captures tenant input, delegates saving to `TenantViewModel`, and navigates to View Tenants with Safe Args. |
| `ViewTenantsFragment` | UI (`ui.tenants`) | Renders newly passed tenant data, triggers phone dialer intents, and observes the tenant roster. |
| `TenantViewModel` | UI (`ui.viewmodel`) | Holds observable UI state, formats display data, and ensures state survival across screen rotation. |
| `TenantRepository` | Data (`data.repository`) | Single source of truth for tenant data; interfaces directly with `TenantDao`. |
| `Tenant` | Data (`data.model`) | Room Entity (`tenants` table) and domain model representing a tenant. |

---

## 8. Data Flow

The application executes an asynchronous, disk-backed data flow powered by Android Room and Kotlin Coroutines:

```text
[AddTenantFragment]
       |
       v
[TenantViewModel] --(viewModelScope.launch)--> [TenantRepository]
                                                      |
                                                      v
                                                [TenantDao]
                                                      |
                                                      v
                                            [Room SQLite Database]
                                                      |
                                                      v (Observable LiveData query)
[DashboardFragment] <--- [TenantViewModel LiveData] --+
[ViewTenantsFragment]
```

> **Lifecycle-Aware State & Permanent Storage:**  
> `ViewModel` and `LiveData` ensure UI state survives configuration changes (such as device rotation), while the underlying Room SQLite database ensures all tenant records persist across application restarts and process terminations.

---

## 9. Team Members and Contributions

The project group consists of exactly six members. Individual stage allocations across Stages 1 through 5 remain subject to official team confirmation. Victoria Mumbi (186643) is confirmed as responsible for Stage 6 (Release & Documentation):

| Team Member | Admission Number | Assigned Stage / Responsibility | Git Branch | Pull Request & Merge Status |
| :--- | :---: | :--- | :--- | :--- |
| Bradley Roche | 193711 | To be confirmed | To be confirmed | To be confirmed |
| Robert Theuri | 184904 | To be confirmed | To be confirmed | To be confirmed |
| Emmanuel Kipruto | 183200 | To be confirmed | To be confirmed | To be confirmed |
| Victoria Mumbi | 186643 | Stage 6: Release & Documentation (Confirmed) | release/v1.0 | PR #7 (Awaiting Review) |
| Aliya Farah | 191254 | To be confirmed | To be confirmed | To be confirmed |
| Zukeina Hassan | 191628 | To be confirmed | To be confirmed | To be confirmed |

### Repository Pull Request History
The Git history of the repository records the following merged and active pull requests. These account usernames represent repository activity and are not equated with individual student identities without team confirmation:
- Pull Request #1: `Add TenantViewModel` (Merged into `master`, authored by GitHub user `Alviskamau`).
- Pull Request #2: `Connect Screens to TenantViewModel` (Merged into `master`, authored on branch by GitHub user `Aliyafarah`, merged by `rtheuri`).
- Pull Request #3: `Resolve merge conflicts: integrate ViewModel and screens into layered architecture` (Merged into `master`, authored and merged by GitHub user `rtheuri`).
- Pull Request #4: `Stage 4B: connect AddTenant and ViewTenants fragments to TenantViewModel` (Merged into `master`, authored and merged by GitHub user `zukena`).
- Pull Request #5: `Implement Room database integration for Tenant data persistence` (Merged into `master`, authored on branch `feature/data-integration` by GitHub user `Kippy330`).
- Pull Request #6: `Add Room entity, DAO and database` (Merged into `master`, authored on branch `feature/room`).
- Pull Request #7: `Stage 6: Release v1.0` (Active on branch `release/v1.0`, authored by `Vmumbi`, awaiting review).

---

## 10. Installation and Setup

### Prerequisites
- **Integrated Development Environment:** Android Studio (compatible with Android Gradle Plugin 9.4+).
- **Android SDK:** Platform SDK 37 (API level 37).
- **Java Development Kit (JDK):** JDK 11 or higher (as defined by `JavaVersion.VERSION_11` in `build.gradle.kts`).
- **Version Control:** Git command-line tools or Android Studio Git integration.

### Steps to Run from Source
1. Clone the repository using Git:
   ```bash
   git clone https://github.com/rtheuri/Tenant_Management-System-2.git
   ```
2. Launch Android Studio, choose **File > Open**, and select the cloned directory `Tenant_Management-System-2`.
3. Allow Gradle to synchronize dependencies. Gradle synchronization must complete successfully before compiling or running the project.
4. If an `SDK location not found` warning appears, verify that your local environment has generated a valid `local.properties` file specifying the SDK path (e.g., `sdk.dir=C\:\\Users\\<User>\\AppData\\Local\\Android\\Sdk`).
5. Ensure a physical device with USB debugging enabled or an Android Virtual Device (AVD) running Android 7.0 (API 24) or higher is connected.
6. Build and execute the project by clicking **Run > Run 'app'** (`Shift + F10`).

---

## 11. Testing and Verification Checklist

The following acceptance checklist is organized according to the **Lab 8 Stage 6** testing criteria.

> **Verification Notice:**  
> All runtime tests currently remain **unverified `[ ]`**. While static code inspection confirms that the corresponding UI, ViewModel, and Room database components are fully integrated in the repository, formal runtime execution on a device or emulator has not yet been conducted.

### Part A: Functional & Navigation Verification
- [ ] **1. User Registration & Login Flow:** Register a new user account, confirm automatic transition to the login screen with pre-filled email, and verify successful login.
- [ ] **2. Initial Dashboard State:** Verify that upon first login, the dashboard accurately displays zero (`0`) tenants and zero rent.
- [ ] **3. Tenant Creation:** Navigate to the Add Tenant screen, enter three distinct tenant records, and save each one.
- [ ] **4. Real-Time Dashboard Updates:** Verify that the dashboard tenant count displays `3` and the total rent displays the correct cumulative sum in `KSh`.
- [ ] **5. Tenant Roster Display:** Navigate to View Tenants and verify that all registered tenants appear in the formatted list, and that the most recently added tenant appears under "Just added".
- [ ] **6. Phone Dialer Action:** Tap the "CALL TENANT" button and verify that the Android phone dialer opens with the correct tenant telephone number pre-filled.
- [ ] **7. Screen Rotation Handling:** Rotate the device on every screen (Login, Dashboard, Add Tenant, View Tenants) to verify that the app does not crash and displayed data is preserved.
- [ ] **8. Navigation Controls:** Open and test every destination in the Navigation Drawer and verify top-level toolbar navigation.
- [ ] **9. Logout Verification:** Select "Logout" from the options menu and confirm that the user session terminates and returns to the Login screen with the backstack cleared.

### Part B: Persistence Verification
- [ ] **10. Application Process Termination:** With tenant data entered, close the application completely and swipe it away from the Android Recent Apps overview.
- [ ] **11. Permanent Data Retention:** Re-launch the application and log in; confirm that all previously entered tenant records remain intact and are loaded from the Room database.

### Part C: Release Verification (Stage 6 Final Step)
- [ ] **12. Physical Device Testing:** Install the generated signed release APK (`app-release.apk`) on a physical Android device and verify complete end-to-end functionality.

---

## 12. Release and Distribution

The official release process is scheduled under **Stage 6** upon the successful review and merging of Stage 5 and Stage 6 PR #7 into `master`.

### Release Procedure (Lab 8 Stage 6)
1. **Branch Creation & PR:** The release branch `release/v1.0` has been created and synced with `master`; Pull Request #7 is open and assigned to Project Lead (to be confirmed) for review.
2. **Version Configuration:** Confirmed that `app/build.gradle.kts` specifies the release version codes inside `defaultConfig`:
   ```kotlin
   versionCode = 1
   versionName = "1.0"
   ```
3. **Signed APK Generation:** Following review and merging of PR #7 into `master`, switch to `master`, pull, and generate a signed release APK in Android Studio via **Build > Generate Signed App Bundle or APK... > APK**.
4. **GitHub Release Publication:**
   - Create a new GitHub Release with tag `v1.0` and title `Tenant Management System v1.0`.
   - Attach the compiled binary `app/release/app-release.apk`.
   - Provide summary release notes and team contributor attributions.

> **Important Security Warning:**  
> The Android release keystore (`.jks` / `.keystore`) and signing credentials must never be committed to the Git repository. The project `.gitignore` must continuously enforce rules blocking `*.jks`, `*.keystore`, and `local.properties`.