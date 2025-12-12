# Switch to Antigravity - IntelliJ IDEA Plugin

An IntelliJ IDEA plugin that allows you to quickly open your current project or file in Antigravity with a single click.

## Features

- 🚀 **Open File in Antigravity**: Right-click on any file in the editor or project view and select "Open in Antigravity"
- 📁 **Open Project in Antigravity**: Right-click on the project root and select "Open Project in Antigravity"
- 🛠️ **Tools Menu Integration**: Quick access from the Tools menu
- 💬 **Notifications**: Get instant feedback when Antigravity is launched

## Installation

### From Source

1. Clone this repository:
   ```bash
   git clone https://github.com/zoowayss/switch2antigravity.git
   cd switch2antigravity
   ```

2. Build the plugin:
   ```bash
   ./gradlew buildPlugin
   ```

3. The plugin ZIP file will be generated in `build/distributions/`

4. In IntelliJ IDEA:
   - Go to `Settings/Preferences` → `Plugins`
   - Click the gear icon ⚙️ → `Install Plugin from Disk...`
   - Select the generated ZIP file
   - Restart IntelliJ IDEA

### From JetBrains Marketplace

*(Coming soon)*

## Usage

### Open a File in Antigravity

1. **From Editor**: Right-click anywhere in the editor → Select "Open in Antigravity"
2. **From Project View**: Right-click on a file → Select "Open in Antigravity"
3. **From Tools Menu**: `Tools` → `Open in Antigravity`

### Open Project in Antigravity

1. **From Project View**: Right-click on the project root → Select "Open Project in Antigravity"
2. **From Tools Menu**: `Tools` → `Open Project in Antigravity`

## Configuration

The plugin uses the Antigravity installation at:
```
/Users/zoowayss/.antigravity/antigravity/bin/antigravity
```

If your Antigravity is installed in a different location, you can modify the `ANTIGRAVITY_PATH` constant in `AntigravityService.java`.

## Requirements

- IntelliJ IDEA 2023.1 or later
- Antigravity installed on your system

## Development

### Building the Plugin

```bash
./gradlew buildPlugin
```

### Running in Development Mode

```bash
./gradlew runIde
```

This will start a new IntelliJ IDEA instance with the plugin installed.

### Project Structure

```
switch2antigravity/
├── src/main/
│   ├── java/com/zoowayss/antigravity/
│   │   ├── AntigravityService.java          # Service for CLI execution
│   │   ├── OpenFileInAntigravityAction.java # Action for opening files
│   │   └── OpenProjectInAntigravityAction.java # Action for opening projects
│   └── resources/META-INF/
│       └── plugin.xml                        # Plugin descriptor
├── build.gradle.kts                          # Gradle build configuration
└── README.md
```

## License

MIT License

## Author

Zoowayss (zoowayss@gmail.com)

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
