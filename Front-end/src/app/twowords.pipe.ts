import { Pipe, PipeTransform } from '@angular/core';
import {CommonModule} from '@angular/common';

@Pipe({name: 'TwoWords'})
export class TwoWords implements PipeTransform {
    str: String = '';
  transform(summary: string): string[] {
    const words = summary.split(' ');
    for (let i = 0; i < 2; i++) {
        this.str += words[i];
    }
    return words;
  }
}
