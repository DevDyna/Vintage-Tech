---
navigation:
  title: Drying Rack
  icon: "vintagetech:drying_rack"
  parent: misc.md
categories:
  - misc
item_ids:
  - vintagetech:drying_rack
position: 0
---

# Drying Rack

A passive item drying process

Require to be placed below a valid block or a rope

It has 1 slots

Can execute multiple recipes at same time

It can speed up when placed above a <ItemLink id="minecraft:campfire"/>

<GameScene zoom="2" interactive={true}>
  <Block  y="4" id="vintagetech:oak_beam" p:axis="x" p:has_rope="true" />
  <Block x="1" y="4" id="vintagetech:oak_beam" p:axis="x" p:has_rope="true" />
  <Block x="-1" y="4" id="vintagetech:oak_beam" p:axis="x" p:has_rope="true" />

  <Block  y="3" id="vintagetech:rope" p:up="true" p:down="true" p:north="false" p:south="false" p:east="false" p:west="false" p:has_corner="false" />
  <Block  y="2" id="vintagetech:drying_rack" />
  <Block  y="0" id="minecraft:campfire" />
</GameScene>

<RecipeFor id="vintagetech:drying_rack" />
