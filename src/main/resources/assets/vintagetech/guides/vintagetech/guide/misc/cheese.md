---
navigation:
  title: Cheese
  icon: "vintagetech:plain_cheese"
  parent: misc.md
categories:
  - misc
item_ids:
  - vintagetech:sealed_cheese
  - vintagetech:aged_cheese
  - vintagetech:plain_cheese
  - vintagetech:aged_cheese_slice
  - vintagetech:plain_cheese_slice
position: 3
---

# Cheese

A unique type of food that can be sealed and will age over time

When broken or clicked with empty hand it will return slices of it

There are 2 states of cheese:

- Static (Plain and Aged)
  - Ready to be eated

- Active (Sealed)
  - Can age over time when placed at a light level below 5

To obtain an aged cheese you need to seal using a <ItemLink id="cakesticklib:honey_solution"/>

To unseal a sealed cheese you need to use an axe

Placing a sealed cheese over specific blocks it will reduce the process of aging

<GameScene zoom="2" interactive={true}>
  <Block  x="2" id="vintagetech:aged_cheese" />
  <Block  x="1" id="vintagetech:sealed_cheese" />
  <Block  x="0" id="vintagetech:plain_cheese" />
</GameScene>

<RecipeFor id="vintagetech:plain_cheese" />

<RecipeFor id="vintagetech:sealed_cheese" />

<RecipeFor id="vintagetech:aged_cheese" />
