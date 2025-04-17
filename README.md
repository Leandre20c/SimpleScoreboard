# SimpleScoreBoard

A lightweight and configurable scoreboard plugin for Paper 1.21+.

## ✨ Features

- ✅ Scoreboard shown on player join
- 🌍 Per-world scoreboard customization
- 🔁 Configurable refresh interval
- 🧩 Full PlaceholderAPI support
- 🔕 Optional player toggle (coming soon)

## 📂 Configuration

Example `config.yml`:

```yaml
update-interval: 5

scoreboards:
  default:
    title: "&aSimpleBoard"
    lines:
      - "&7Name: &f%player_name%"
      - "&7Kills: &c%statistic_player_kills%"
  world_nether:
    title: "&cNether Mode"
    lines:
      - "&7Watch out, %player_name%"
