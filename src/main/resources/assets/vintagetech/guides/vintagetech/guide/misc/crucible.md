---
navigation:
  title: Crucible
  icon: "vintagetech:crucible"
  parent: misc.md
categories:
  - misc
item_ids:
  - vintagetech:crucible
position: 4
---

# Crucible

A low-tech vessel for heat-based item and fluid processing

Require to be placed over a <ItemLink id="minecraft:campfire"/> to work

It has 8 slots

Can process multiple recipes at same time

Can recieve fluids extracted by <ItemLink id="vintagetech:tree_tap"/>

<GameScene zoom="2" interactive={true}>
  <Block  y="1" id="vintagetech:crucible" />
  <Block  y="0" id="minecraft:campfire" />
</GameScene>

<RecipeFor id="vintagetech:crucible" />
