---
navigation:
  title: Crushing Tub
  icon: "vintagetech:crushing_tub"
  parent: misc.md
categories:
  - misc
item_ids:
  - vintagetech:crushing_tub
  - vintagetech:mesh
position: 5
---

# Crushing Tub

A low-tech item-pressing to produce items and fluids

It has 1 slots

Execute one recipe every time a Player or an <ItemLink id="minecraft:armor_stand"/> fall over it

Can recieve fluids extracted by <ItemLink id="vintagetech:tree_tap"/>

Some recipes could require a <ItemLink id="vintagetech:mesh"/> to work

<GameScene zoom="2" interactive={true}>
  <Block x="2" y="0" id="vintagetech:crushing_tub" p:has_mesh="false" />
  <Block x="0" y="0" id="vintagetech:crushing_tub" p:has_mesh="true" />
</GameScene>

<RecipeFor id="vintagetech:crushing_tub" />
