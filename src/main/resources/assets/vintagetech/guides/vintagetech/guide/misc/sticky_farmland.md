---
navigation:
  title: Sticky Farmland
  icon: "vintagetech:sulfur_goo"
  parent: misc.md
categories:
  - misc
item_ids:
  - vintagetech:sticky_farmland
  - vintagetech:sulfur_goo
position: 7
---

# Sticky Farmland

An improved farmland that will boost +5% the grow of the crop above foreach moisture stage and prevents trampling but when it doesn't have any water source near it will turn into dirt burning the crop above

Can be obtained by right clicking a farmland with a <ItemLink id="vintagetech:sulfur_goo"/>

<Row>
<ItemImage id="minecraft:farmland"/>
![a](assets/goo.png)
<ItemImage id="vintagetech:sticky_farmland" />
</Row>

<GameScene zoom="2" interactive={true}>
  <Block x="0" id="vintagetech:sticky_farmland" p:moisture="0" />
  <Block x="1" id="vintagetech:sticky_farmland" p:moisture="7" />
  <Block x="2" id="minecraft:dirt" />
  <Block x="2" y="1" id="minecraft:fire" />
</GameScene>

Using an Axe you can revert it into a normal farmland

<Row>
<ItemImage id="vintagetech:sticky_farmland"/>
![a](assets/axe.png)
<ItemImage id="minecraft:farmland" />
</Row>
