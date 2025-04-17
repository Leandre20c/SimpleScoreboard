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
# SimpleScoreBoard Configuration
# ------------------------------
# - update-interval: how often the scoreboard refreshes (in seconds)
# - scoreboards: define the content per world
#   - "default" will be used if no specific scoreboard exists for a world
#   - you can use PlaceholderAPI placeholders (e.g., %player_name%)

update-interval: 5

scoreboards:
  default:
    title: "&c&lServer Name"
    lines:
      - "§e▸ §6General Info"
      - " §7Name: §f%player_name%"
      - " §7Rank: %luckperm_prefix%"
      - " §7Money: §c%vault_eco_balance% ⛃"
      - "§f"
      - "§e▸ §6Clan"
      - " §7Name: §f%clan_name%"
      - " §7Role: §f%clan_rank%"
      - " §7Members: §f%clan_member_count%"

  world_nether:
    title: "&c&lNether"
    lines:
      - "&7Name: &f%player_name%"
      - "&7World: &4%player_world%"
      - "&7Blocks Walked: &f%statistic_walk_one_cm%"

  world_the_end:
    title: "&5&lThe End Awaits"
    lines:
      - "&7Name: &f%player_name%"
      - "&7Dragon Kills: &d%statistic_ender_dragon_killed%"
      - "&7Score: &f%scoreboard_score%"

