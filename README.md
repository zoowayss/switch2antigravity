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

The plugin automatically detects your Antigravity installation by searching in the following order:

1. **Environment Variable**: `ANTIGRAVITY_PATH` environment variable
2. **Common Installation Locations**:
   - `~/.antigravity/antigravity/bin/antigravity`
   - `~/.local/bin/antigravity`
   - `~/bin/antigravity`
   - `/usr/local/bin/antigravity`
   - `/usr/bin/antigravity`
   - `/opt/antigravity/bin/antigravity`
3. **System PATH**: Searches for `antigravity` in your system PATH
4. **Default Fallback**: `~/.antigravity/antigravity/bin/antigravity`

### Custom Installation Path

If your Antigravity is installed in a non-standard location, you can set the `ANTIGRAVITY_PATH` environment variable:

```bash
# In your ~/.zshrc or ~/.bashrc
export ANTIGRAVITY_PATH="/path/to/your/antigravity"
```

After setting the environment variable, restart IntelliJ IDEA for the changes to take effect.

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
