export class Filter {
    categoryId: number | undefined;
    attributeId: number | undefined;
    difficultyId: number | undefined;
    priceFrom: number | undefined;
    priceTo: number | undefined;
    durationFrom: number | undefined;
    durationTo: number | undefined;

    constructor(categoryId?: number, attributeId?: number, difficultyId?: number, priceFrom?: number, priceTo?: number, durationFrom?: number , durationTo?: number){
       this.categoryId = categoryId || undefined;
       this.attributeId = attributeId || undefined;
       this.difficultyId = difficultyId || undefined;
       this.durationFrom = durationFrom || undefined;
       this.durationTo = durationTo || undefined;
       this.priceFrom = priceFrom || undefined;
       this.priceTo = priceTo || undefined;

    }
  }